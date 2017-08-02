package com.wf.ivankov.shop.service;

import com.wf.ivankov.shop.domain.HistoricalAction;

/**
 * @author Ivankov_A
 *
 */
public interface HistoryService {

	void save (HistoricalAction action);
}
