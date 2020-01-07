package com.wwy.test.mybatisplustest.service;

import com.wwy.entry.APIEntry;
import com.wwy.entry.Order;

public interface MBPService {

	APIEntry get();

	APIEntry updata(Order order);

}
