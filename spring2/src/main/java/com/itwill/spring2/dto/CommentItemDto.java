//package com.itwill.spring2.dto;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
//import com.itwill.spring2.repository.Comment;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
package com.itwill.spring2.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.itwill.spring2.repository.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class CommentItemDto {
    private Integer id;
    private String ctext;
    private String username; 
    
    //Response (서버 -> 클라이언트) 로 전달할 때에는 @JsonFormat 을 사용,
    //Request(클라이언트 -> 서버)로 전달할 때는 @DateTimeFormat 을 사용한다.
    //(post요청시 request에서는 @jsonFormat 사용 가능)
    @JsonFormat(shape = Shape.STRING)//-> Json 날짜 형식 변환 처리. 예) "2024-06-14T15:56:54.79"
    private LocalDateTime modifiedTime;

    // Comment 타입의 객체를 CommentItemDto 타입 객체로 변환해서 리턴하는 메서드.
    public static CommentItemDto fromEntity(Comment comment) {
        return CommentItemDto.builder()
                .id(comment.getId())
                .ctext(comment.getCtext())
                .username(comment.getUsername())
                .modifiedTime(comment.getModifiedTime())
                .build();
    }
}







//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class CommentItemDto {//DB에서 select한 결과를 Dto로 만들어 내야함.
//	private Integer id;
//	private String ctext;
//	private String username;
//	private Timestamp modifiedTime;
//	//private LocalDateTime modifiedTime;
//	
//	//Comment 타입의 객체를 아규먼트로 전달 받아서 CommentItemDto타입 객체로 변환해서 리턴해주는 메서드
//	public static CommentItemDto fromEntity(Comment comment) {
//		
//		return CommentItemDto.builder().id(comment.getId())
//				.ctext(comment.getCtext())
//				.username(comment.getUsername())
//				.modifiedTime(Timestamp.valueOf(comment.getModifiedTime()))
//				.build();
//	}
//	
//}
