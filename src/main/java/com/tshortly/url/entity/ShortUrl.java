package com.tshortly.url.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@DynamicUpdate
@DynamicInsert
@TableGenerator(name="SHORT_URL_ID_GENERATOR", pkColumnValue = "SHORT_URL", initialValue=100000, allocationSize=1)
@Table(
        name = "SHORT_URLS",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_SHORT_URL_CODE", columnNames = "SHORT_URL_CODE")
        },
        indexes = {
                @Index(name = "IDX_SHORT_URL_CODE", columnList = "SHORT_URL_CODE")
        }
)
public class ShortUrl {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="SHORT_URL_ID_GENERATOR")
    private Long id;

    @Column(name = "SHORT_URL_CODE", nullable = false, length = 20)
    private String shortUrlCode;

    @Column(name = "LONG_URL", nullable = false, length = 2048)
    private String longUrl;
    private Long ownerId;
    private boolean trackable;
    @Column(name = "CREATED_AT", nullable = false)
    private ZonedDateTime createdAt;


    public static ShortUrl create(String shortUrlCode,
                                  String longUrl,
                                  Long ownerId,
                                  boolean trackable) {
        return ShortUrl.builder().shortUrlCode(shortUrlCode).longUrl(longUrl).ownerId(ownerId)
                .trackable(trackable).createdAt(ZonedDateTime.now()).build();
    }

}