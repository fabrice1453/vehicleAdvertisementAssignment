/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sm360.vehicleAdvertisement.dto.api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *
 * @author fabrice
 *
 * Generic API response wrapper.
 *
 * @param <T> the type of the response data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    
    /**
     * Indicates whether the API request was successful.
     */
    private boolean success;
    
    /**
     * The message associated with the API response.
     */
    private String message;
    
    /**
     * The data returned in the API response.
     */
    private T data;
    
    /**
     * Creates a new API response.
     *
     * @param success  the success flag
     * @param message  the response message
     */
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    /**
     * Creates a new success API response.
     *
     * @param data  the response data
     * @param <T>   the type of the response data
     * @return the success API response
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Success", data);
    }
    
    /**
     * Creates a new error API response.
     *
     * @param message  the response error message
     * @param <T>      the type of the response data
     * @return the error API response
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
