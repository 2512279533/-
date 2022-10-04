package com.guigu.edu.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectNestEdVo {

    private String id;

    private String title;

    private List<SubjectVo> children = new ArrayList<>();
}
