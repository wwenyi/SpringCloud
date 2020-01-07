package com.wwy.test.upload.service;

import org.springframework.web.multipart.MultipartFile;

import com.wwy.entry.APIEntry;

public interface UpLoadService {

	APIEntry upLoad(MultipartFile multipartFile);

}
