package org.aiml.libs.common.file

import org.springframework.web.multipart.MultipartFile;

interface FileStorage {
  fun uploadFile(file: MultipartFile, path: String): String
  fun getUrl(path: String?): String
}
