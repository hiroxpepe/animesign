package com.studio.meowtoon.animesign.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Data
@Entity
@Table(name="timeline")
public class Book {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true)
    private Long id;
    
    @Column(name="title") // タイトル
    private String title;
    
    @Column(name="volume") // 巻
    private Long volume;
    
    @Column(name="chapter") // 章
    private Long chapter;
    
    @Column(name="episode") // 話
    private Long episode;
    
//    @Column(name="page") // 項(ページ)
//    private Long page;
//    
//    @Column(name="panel") // 枠(コマ)
//    private Long panel;
    
    @Column(name="pattern") // 纏(まとい)
    private Long pattern;
    
}