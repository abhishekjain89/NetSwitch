package com.netswitch.helpers;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.netswitch.models.Measure;
import com.netswitch.models.Ping;
import com.netswitch.utils.CommandLineUtil;
import com.netswitch.utils.ParseUtil;

public class TracerouteHelper {
	
	public static CommandLineUtil cmdUtil;
	public static String output;
	
	
	/**
	 * Pinghelp helps run ping command by creating cmd and inputs
	 * @return
	 */
	public static String tracerouteHelp(String ip_address) {
		
		String options = "-lv -w 3";
		cmdUtil = new CommandLineUtil();
		
		output = cmdUtil.runCommand("traceroute", ip_address, options);
		
		
		//Measure ping_measurement = ParseUtil.PingParser(output);

		
		return "";
	}

	

}
