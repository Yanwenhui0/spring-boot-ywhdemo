package com.springboot.fastdfs.util;

import com.springboot.fastdfs.entity.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/1
 */
public class FastdfsUtils {
    private static Logger logger = LoggerFactory.getLogger(FastdfsUtils.class);

    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();

            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!", e);
        }
    }

    public static String upload(FastDFSFile file) {
        logger.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", file.getName());
        metaList[1] = new NameValuePair("fileExtName", file.getExt());
        metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getContent().length));

        long startTime = System.currentTimeMillis();
        String uploadResult = null;
        StorageClient1 storageClient = null;
        try {
            storageClient = getStorageClient();
            uploadResult = storageClient.upload_file1(file.getContent(), file.getExt(), metaList);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        } catch (Exception e) {
            logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
        }
        logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

        if (uploadResult == null && storageClient != null) {
            logger.error("upload file fail, error code:" + storageClient.getErrorCode());
            return "";
        }

        logger.info("upload file successfully!!!" + "filePath:" + uploadResult);
        return uploadResult;
    }

    public static FileInfo getFile(String filePath) {
        try {
            StorageClient1 storageClient = getStorageClient();
            return storageClient.get_file_info1(filePath);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    public static NameValuePair[] getFileMetadata(String filePath) {
        try {
            StorageClient1 storageClient = getStorageClient();
            return storageClient.get_metadata1(filePath);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    public static InputStream downFile(String filePath) {
        int indexOf = filePath.indexOf("/");
        String groupName = filePath.substring(0, indexOf);
        String remoteFileName = filePath.substring(indexOf + 1);
        try {
            StorageClient1 storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    public static void deleteFile(String filePath) throws Exception {
        int indexOf = filePath.indexOf("/");
        String groupName = filePath.substring(0, indexOf);
        String remoteFileName = filePath.substring(indexOf + 1);
        StorageClient1 storageClient = getStorageClient();
        int i = storageClient.delete_file(groupName, remoteFileName);
        logger.info("delete file successfully!!!" + i);
    }

    public static StorageServer[] getStoreStorages(String groupName)
            throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }

    public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    public static String getTrackerUrl() throws IOException {
        return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

    private static StorageClient1 getStorageClient() throws Exception {
        TrackerClient tracker = new TrackerClient();
        TrackerServer trackerServer = tracker.getTrackerServer();
        StorageServer storageServer = tracker.getStoreStorage(trackerServer);
        StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
        return storageClient;
    }

    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }
}
