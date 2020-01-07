package com.wwy.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wwy.entry.APIEntry;

public interface UserService {

	APIEntry selectAll(Integer page, Integer type,HttpServletRequest request,HttpServletResponse response);

}
