package com.netswitch.helpers;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.netswitch.models.Address;
import com.netswitch.models.Measure;
import com.netswitch.models.Ping;
import com.netswitch.utils.CommandLineUtil;
import com.netswitch.utils.ParseUtil;

public class PingHelper {
	
	public static CommandLineUtil cmdUtil;
	public static String pingOutput;
	
	
	/**
	 * Pinghelp helps run ping command by creating cmd and inputs
	 * @return
	 */
	public static Ping pingHelp(Address address, int count) {
		Ping p 			= null;
		String ipDst 	= address.getIp();
		String cmd 		= "ping";
		String options 	= "-c " + count;
		String output 	= "";

		cmdUtil = new CommandLineUtil();
		output = cmdUtil.runCommand(cmd, ipDst, options);
		pingOutput = output;
		
		Measure ping_measurement = ParseUtil.PingParser(output);

		Socket conn;
		String ipSrc = "";
		try {
			conn = new Socket("www.google.com", 80);
			ipSrc = conn.getLocalAddress().toString(); 
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		p=new Ping(ipSrc, address ,ping_measurement);
		
		
		return p;
	}

	public static String getPingOutput() {
		return pingOutput;
	}

	
}