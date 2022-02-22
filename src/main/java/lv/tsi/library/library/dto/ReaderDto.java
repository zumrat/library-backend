package lv.tsi.library.library.dto;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ReaderDto {

    private final Long id;
    private final String email;
    private final String address;
    private final String fullName;
    private final String phoneNumber;
    private final List<CheckOutDto> checkOuts;

    private ReaderDto(Long id, String email, String address, String fullName, String phoneNumber, List<CheckOutDto> checkOuts) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.checkOuts = checkOuts;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<CheckOutDto> getCheckOuts() {
        return checkOuts;
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

    public static class Builder {
        private Long id;
        private String email;
        private String address;
        private String fullName;
        private String phoneNumber;
        private List<CheckOutDto> checkOuts;

        Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder checkOuts(List<CheckOutDto> checkOuts) {
            this.checkOuts = checkOuts;
            return this;
        }

        public ReaderDto build() {
            return new ReaderDto(id, email, address, fullName, phoneNumber, checkOuts);
        }

    }
}
