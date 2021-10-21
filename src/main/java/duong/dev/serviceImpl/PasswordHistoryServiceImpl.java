package duong.dev.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.common.Status;
import duong.dev.entity.Account;
import duong.dev.entity.PasswordHistory;
import duong.dev.repository.PasswordHistoryRepository;
import duong.dev.service.PasswordHistoryInterface;

@Component
public class PasswordHistoryServiceImpl implements PasswordHistoryInterface{
	@Autowired private PasswordHistoryRepository passwordHistoryRepo;
	
	public PasswordHistory get(Account account) {
		return passwordHistoryRepo.findByAccountSatusOn(account.getId());
	}
	
	public PasswordHistory create(Account account, String strRoandom) {
		LocalDateTime dateTime = java.time.LocalDateTime.now().plusMinutes(5);
		PasswordHistory passwordHistory = new PasswordHistory();
		passwordHistory.setPassword(account.getPassword());
		passwordHistory.setDatetime(dateTime+"");
		passwordHistory.setAccount(account);
		passwordHistory.setStatus(Status.STATUS_HOAT_DONG);
		passwordHistory.setCodeComfirm(strRoandom);
		passwordHistoryRepo.save(passwordHistory);
		return passwordHistory;
	}
	
	public PasswordHistory updateStatus(Account account) {
		PasswordHistory passwordHistorie = passwordHistoryRepo.findByAccountSatusOn(account.getId());
		if(passwordHistorie==null) 
			return null;
		passwordHistorie.setStatus(Status.STATUS_KHONG_HOAT_DONG);
		passwordHistoryRepo.save(passwordHistorie);
		return passwordHistorie;
	}
	
	public List<PasswordHistory> deleteAllOff(Account account) {
		List<PasswordHistory> passwordHistories = passwordHistoryRepo.findByAccountSatusOff(account.getId());
		passwordHistoryRepo.deleteAll(passwordHistories);;
		return passwordHistories;
	}
}
