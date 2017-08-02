package com.wf.ivankov.shop.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.wf.ivankov.shop.domain.HistoricalAction;

/**
 * @author Ivankov_A
 *
 */
public interface HistoryRepository extends PagingAndSortingRepository<HistoricalAction, Integer> {

}
