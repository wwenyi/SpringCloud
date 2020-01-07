package com.wwy.test.exporttest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wwy.entry.APIEntry;

public interface ExportService {

	APIEntry get(List<Long> id,HttpServletResponse response,HttpServletRequest request);

}
