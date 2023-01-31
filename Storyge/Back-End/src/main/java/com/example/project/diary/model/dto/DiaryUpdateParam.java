package com.example.project.diary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryUpdateParam {

    private Long diaryId;
    private Long emoticonId;

    private String diaryContent;
    private int scope;
    private int update_cnt;
    private String analizedResult;

}
