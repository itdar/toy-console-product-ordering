package domain.product;

import java.util.Objects;

public class ProductNumber {
    private int productNumber;

    private ProductNumber(int productNumber) {
        validate(productNumber);

        this.productNumber = productNumber;
    }

    private void validate(int productNumber) {
        if (productNumber < 0) {
            throw new IllegalArgumentException("유효하지 않은 상품번호");
        }
    }

    public static ProductNumber of(int quantity) {
        return new ProductNumber(quantity);
    }

    public int get() {
        return productNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(productNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductNumber that = (ProductNumber) o;
        return productNumber == that.productNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNumber);
    }
}
