package lv.tsi.library.library.dto;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ReaderDto {

    private Long id;
    private String email;
    private String address;
    private String fullName;
    private String phoneNumber;

    private List<CheckOutDto> checkOuts;

    public Long getId() {
        return id;
    }

    public ReaderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ReaderDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ReaderDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ReaderDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ReaderDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<CheckOutDto> getCheckOuts() {
        return checkOuts;
    }

    public ReaderDto setCheckOuts(List<CheckOutDto> checkOuts) {
        this.checkOuts = checkOuts;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaderDto readerDto = (ReaderDto) o;
        return Objects.equals(id, readerDto.id) && Objects.equals(email, readerDto.email) && Objects.equals(address, readerDto.address) && Objects.equals(fullName, readerDto.fullName) && Objects.equals(phoneNumber, readerDto.phoneNumber) && Objects.equals(checkOuts, readerDto.checkOuts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, address, fullName, phoneNumber, checkOuts);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ReaderDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("email='" + email + "'")
                .add("address='" + address + "'")
                .add("fullName='" + fullName + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("checkOuts=" + checkOuts)
                .toString();
    }
}
