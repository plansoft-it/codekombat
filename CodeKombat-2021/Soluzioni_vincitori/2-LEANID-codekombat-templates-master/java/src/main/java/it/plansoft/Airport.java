package it.plansoft;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

	private String Code;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Airport airport = (Airport) o;
		return Objects.equals(Code, airport.Code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Code);
	}
}
