package com.example.auth_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers") // Sử dụng số nhiều để tránh xung đột với từ khóa SQL
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "company_name", nullable = false, length = 255)
    private String companyName;


    @Column(name = "address", nullable = false, length = 255)
    private String address;


    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Thành phố phải chỉ chứa chữ cái và khoảng trắng.")
    @Column(name = "city", nullable = false, length = 100)
    private String city;


    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;


    @Column(name = "year_of_birth", nullable = false)
    private Integer namSinh;

    @Column(name = "region", length = 100)
    private String region;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Tên công ty không được để trống.") String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(@NotBlank(message = "Tên công ty không được để trống.") String companyName) {
        this.companyName = companyName;
    }

    public @NotBlank(message = "Địa chỉ không được để trống.") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Địa chỉ không được để trống.") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Thành phố không được để trống.") @Pattern(regexp = "[a-zA-Z\\s]+", message = "Thành phố phải chỉ chứa chữ cái và khoảng trắng.") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "Thành phố không được để trống.") @Pattern(regexp = "[a-zA-Z\\s]+", message = "Thành phố phải chỉ chứa chữ cái và khoảng trắng.") String city) {
        this.city = city;
    }

    public @NotBlank(message = "Quốc gia không được để trống.") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank(message = "Quốc gia không được để trống.") String country) {
        this.country = country;
    }

    public @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "Mã bưu điện phải chứa cả chữ cái và số, không có ký tự đặc biệt.") String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "Mã bưu điện phải chứa cả chữ cái và số, không có ký tự đặc biệt.") String postalCode) {
        this.postalCode = postalCode;
    }

    public @NotNull(message = "Năm sinh không được để trống.") Integer getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(@NotNull(message = "Năm sinh không được để trống.") Integer namSinh) {
        this.namSinh = namSinh;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
