package com.thxforservice.counselling.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="CounselingAdmin", description = "상담 관리자 API")
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class CounsellingAdminController {


}
