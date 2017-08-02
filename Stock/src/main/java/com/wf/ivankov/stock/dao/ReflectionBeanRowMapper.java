package com.wf.ivankov.stock.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

/**
 * @author Ivankov_A
 *
 */
public class ReflectionBeanRowMapper<T> implements RowMapper<T> {

	private static final Logger LOG = LoggerFactory.getLogger(ReflectionBeanRowMapper.class);
	private Class<T> bean;
	private Map<String, Field> fieldMap = new HashMap<>();
	private Field[] cacheFields;

	public ReflectionBeanRowMapper(Class<T> beanCls) {
		LOG.debug("Creating Bean Mapper for: {}", beanCls.getName());
		this.bean = beanCls;
		processFields(beanCls.getDeclaredFields());
		Class<? super T> superClass = beanCls.getSuperclass();//First superClass
		while (!superClass.equals(Object.class)) {
			processFields(superClass.getDeclaredFields());
			superClass = superClass.getSuperclass();//next and next superClass
		}
	}

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T theResult = BeanUtils.instantiate(this.bean);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(theResult);

		bw.setAutoGrowNestedPaths(true);

		ResultSetMetaData metaData = rs.getMetaData();
		int colCount = metaData.getColumnCount();
		for (int index = 0; index < colCount; index++) {
			try {
				//Initializing cache - Columns started from 1... (i + 1)
				if (cacheFields == null) {
					cacheFields = new Field[colCount];
					for (int i = 0; i < colCount; i++) {
						String column = JdbcUtils.lookupColumnName(metaData, i + 1);
						cacheFields[i] = fieldMap.get(column);
					}
				}

				Object value = JdbcUtils.getResultSetValue(rs, index + 1, cacheFields[index].getDeclaringClass());
				if (cacheFields[index] != null) {
					String fieldName = cacheFields[index].getName();
					bw.setPropertyValue(fieldName, value);
				} else {
					LOG.warn("Warning: there is no field:{} in the bean:{}, value:{}", JdbcUtils.lookupColumnName(metaData, index + 1), bean.getName(), value);
				}
			} catch (TypeMismatchException | NotWritablePropertyException e) {
				LOG.warn("Exception while setting property: {}", e.getMessage());
				LOG.trace("", e);
			}
		}

		return theResult;
	}

	private void processFields(Field[] aFields) {
		for (Field nextField : aFields) {
			if (nextField.isAnnotationPresent(Transient.class) || Modifier.isStatic(nextField.getModifiers())) {
				continue;
			}
	
			if (nextField.isAnnotationPresent(Column.class)) {
				fieldMap.put(nextField.getAnnotation(Column.class).name(), nextField);
			} else {
				String[] splitted = nextField.getName().split("(?=[A-Z])");
				String colName = String.join("_", Arrays.asList(splitted).stream().map(s -> s.toUpperCase()).collect(Collectors.toList()));
				fieldMap.put(colName, nextField);
			}
		}
	}

}
