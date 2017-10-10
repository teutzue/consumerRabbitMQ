package client;

import java.rmi.RemoteException;
import java.text.RuleBasedCollator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.bank.credit.web.service.CreditScoreService_PortType;
import org.bank.credit.web.service.CreditScoreService_ServiceLocator;

import net.RuleBase;
import net.RuleBasePortBindingStub;
import net.RuleBaseProxy;
import net.RuleBaseService;
import net.RuleBaseServiceLocator;

public class RuleBaseClient 
{
	public String[] getBanks(int score)
	{
		String[] banks = null;
		RuleBaseService rbsl = new RuleBaseServiceLocator();
		try 
		{
			
			RuleBase port=rbsl.getRuleBasePort();
			banks=port.getBanks(score);
						
		} catch (ServiceException | RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return banks;
	}

}
