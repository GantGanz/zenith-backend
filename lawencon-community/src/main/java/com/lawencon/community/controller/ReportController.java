package com.lawencon.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.constant.ReportConstant;
import com.lawencon.community.dto.report.ReportData;
import com.lawencon.community.dto.user.UserRes;
import com.lawencon.community.service.ReportService;
import com.lawencon.community.service.UserService;
import com.lawencon.security.principal.PrincipalService;
import com.lawencon.util.JasperUtil;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("reports")
public class ReportController {
	@Autowired
	private UserService userService;
	@Autowired
	private JasperUtil jasperUtil;
	@Autowired
	private PrincipalService principalService;
	@Autowired
	private ReportService reportService;

	@GetMapping("count-member-income")
	public ResponseEntity<Long> countMemberIncome(@RequestParam("startDate") final String startDate, @RequestParam("endDate") final String endDate) {
		final UserRes user = userService.getById(principalService.getAuthPrincipal());
		final Long res = reportService.countMemberIncome(user.getData().getId(), startDate, endDate);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("member-income")
	public ResponseEntity<?> getMemberIncome(@RequestParam("startDate") final String startDate, @RequestParam("endDate") final String endDate) throws Exception {
		final UserRes user = userService.getById(principalService.getAuthPrincipal());
		final List<ReportData> data = reportService.getMemberIncome(user.getData().getId(), startDate,
				endDate);
		final Map<String, Object> map = new HashMap<>();
		map.put("reportTitle", ReportConstant.MEMBER_INCOME.getReportTitleEnum());
		map.put("reportType", ReportConstant.MEMBER_INCOME.getReportTypeEnum() + user.getData().getEmail());
		map.put("company", user.getData().getCompany());
		final String dateRange = reportService.formatDateRange(startDate, endDate);
		map.put("dateRange", dateRange);
		final byte[] out = jasperUtil.responseToByteArray(data, map, "member-income");
		final String fileName = "member-income.pdf";
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(out);
	}

	@GetMapping("superadmin-income")
	public ResponseEntity<?> getSystemIncome(@RequestParam("startDate") final String startDate, @RequestParam("endDate") final String endDate) throws Exception {
		final UserRes user = userService.getById(principalService.getAuthPrincipal());
		final List<ReportData> data = reportService.getSystemIncome(startDate, endDate);
		final Map<String, Object> map = new HashMap<>();
		map.put("reportTitle", ReportConstant.SUPERADMIN_INCOME.getReportTitleEnum());
		map.put("reportType", ReportConstant.SUPERADMIN_INCOME.getReportTypeEnum() + user.getData().getEmail());
		map.put("company", user.getData().getCompany());
		final String dateRange = reportService.formatDateRange(startDate, endDate);
		map.put("dateRange", dateRange);
		final byte[] out = jasperUtil.responseToByteArray(data, map, "superadmin-income");
		final String fileName = "superadmin-income.pdf";
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(out);
	}

	@GetMapping("count-activity-member")
	public ResponseEntity<Long> countActivityMember(@RequestParam("startDate") final String startDate, @RequestParam("endDate") final String endDate) {
		final UserRes user = userService.getById(principalService.getAuthPrincipal());
		final Long res = reportService.countActivityMember(user.getData().getId(), startDate, endDate);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("activity-member")
	public ResponseEntity<?> getActivityReportMember(@RequestParam("startDate") final String startDate, @RequestParam("endDate") final String endDate) throws Exception {
		final UserRes user = userService.getById(principalService.getAuthPrincipal());
		final List<ReportData> data = reportService.getMemberActivity(user.getData().getId(), startDate,
				endDate);
		final Map<String, Object> map = new HashMap<>();
		map.put("reportTitle", ReportConstant.ACTIVITY_MEMBER.getReportTitleEnum());
		map.put("reportType", ReportConstant.ACTIVITY_MEMBER.getReportTypeEnum() + user.getData().getEmail());
		map.put("company", user.getData().getCompany());
		final String dateRange = reportService.formatDateRange(startDate, endDate);
		map.put("dateRange", dateRange);
		final byte[] out = jasperUtil.responseToByteArray(data, map, "activity-member");
		final String fileName = "activity-member.pdf";
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(out);
	}

	@GetMapping("count-activity-superadmin")
	public ResponseEntity<Long> countActivitySuperAdmin(@RequestParam("startDate") final String startDate, @RequestParam("endDate") final String endDate) {
		final Long res = reportService.countActivitySuperAdmin(startDate, endDate);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("activity-superadmin")
	public ResponseEntity<?> getActivityReportSuperAdmin(@RequestParam("startDate") final String startDate, @RequestParam("endDate") final String endDate) throws Exception {
		final UserRes user = userService.getById(principalService.getAuthPrincipal());
		final List<ReportData> data = reportService.getActivitySuperAdmin(startDate, endDate);
		final Map<String, Object> map = new HashMap<>();
		map.put("reportTitle", ReportConstant.ACTIVITY_SUPERADMIN.getReportTitleEnum());
		map.put("reportType", ReportConstant.ACTIVITY_SUPERADMIN.getReportTypeEnum() + user.getData().getEmail());
		map.put("company", user.getData().getCompany());
		final String dateRange = reportService.formatDateRange(startDate, endDate);
		map.put("dateRange", dateRange);
		final byte[] out = jasperUtil.responseToByteArray(data, map, "activity-superadmin");
		final String fileName = "activity-superadmin.pdf";
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(out);
	}
}
