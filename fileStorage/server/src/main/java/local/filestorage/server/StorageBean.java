package local.filestorage.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton(name = "StorageBean")
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Startup
public class StorageBean {

	@Resource
	private String storageFolder;

	@PostConstruct
	public void init() {

	}

	@PreDestroy
	public void terminate() {

	}

	public String getStorageFolder() {
		return storageFolder;
	}
}
