package com.gk.system.web;

import com.gk.common.tools.R;
import com.gk.system.dto.SystemDTO;
import com.sun.management.OperatingSystemMXBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 系统接口
 * 
 * @author Lowen
 */
@RestController
@Tag(name = "系统接口")
public class SystemController {

	@GetMapping("sys/info")
	@Operation(summary = "系统信息")
	public R<?> info(){
		OperatingSystemMXBean osmx = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
		SystemDTO dto = new SystemDTO();
		dto.setSysTime(System.currentTimeMillis());
		dto.setOsName(System.getProperty("os.name"));
		dto.setOsArch(System.getProperty("os.arch"));
		dto.setOsVersion(System.getProperty("os.version"));
		dto.setUserLanguage(System.getProperty("user.language"));
		dto.setUserDir(System.getProperty("user.dir"));
		dto.setTotalPhysical(osmx.getTotalPhysicalMemorySize()/1024/1024);
		dto.setFreePhysical(osmx.getFreePhysicalMemorySize()/1024/1024);
		dto.setMemoryRate(BigDecimal.valueOf((1-osmx.getFreePhysicalMemorySize()*1.0/osmx.getTotalPhysicalMemorySize())*100).setScale(2, RoundingMode.HALF_UP));
		dto.setProcessors(osmx.getAvailableProcessors());
		dto.setJvmName(System.getProperty("java.vm.name"));
		dto.setJavaVersion(System.getProperty("java.version"));
		dto.setJavaHome(System.getProperty("java.home"));
		dto.setJavaTotalMemory(Runtime.getRuntime().totalMemory()/1024/1024);
		dto.setJavaFreeMemory(Runtime.getRuntime().freeMemory()/1024/1024);
		dto.setJavaMaxMemory(Runtime.getRuntime().maxMemory()/1024/1024);
		dto.setUserName(System.getProperty("user.name"));
		dto.setSystemCpuLoad(BigDecimal.valueOf(osmx.getSystemCpuLoad()*100).setScale(2, RoundingMode.HALF_UP));
		dto.setUserTimezone(System.getProperty("user.timezone"));
		return R.ok(dto);
	}

}