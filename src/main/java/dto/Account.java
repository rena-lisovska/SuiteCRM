package dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Builder.Default
    private String name = "Test_name";
    @Builder.Default
    private String phone = "";
    @Builder.Default
    private String website = "";
    @Builder.Default
    private String fax = "";
    @Builder.Default
    private String email_address = "";
    @Builder.Default
    private String billingStreet = "";
    @Builder.Default
    private String billingCity = "";
    @Builder.Default
    private String billingState = "";
    @Builder.Default
    private String billingPostalCode = "";
    @Builder.Default
    private String billingCountry = "";
    @Builder.Default
    private String shippingStreet  = "";
    @Builder.Default
    private String shippingCity = "";
    @Builder.Default
    private String shippingState = "";
    @Builder.Default
    private String shippingPostalCode = "";
    @Builder.Default
    private String shippingCountry = "";
    @Builder.Default
    private String description = "";
    @Builder.Default
    private String type = "";
    @Builder.Default
    private String industry = "";
}
