package com.sarabarbara.adoption.responses;

import lombok.*;

import java.util.List;

/**
 * SearchResponse class
 *
 * @author sarabarbaraam
 * @version 1.0
 * @since 10/04/2025
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SearchResponse<T> {

    /**
     * The results
     */

    private List<T> results;

    /**
     * The totalResults
     */

    private int totalResults;

    /**
     * The currentPage
     */

    private int currentPage;

    /**
     * The totalPage
     */

    private int totalPage;

    /**
     * The message
     */

    private String message;

}
