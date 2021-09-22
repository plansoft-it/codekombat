package it.plansoft;

import lombok.Builder;
import org.jgrapht.graph.DefaultWeightedEdge;

@Builder
public class FlightRelation extends DefaultWeightedEdge {

	private Long id;
	private Airport from;
	private Airport to;
	private double totalPrice;

	public FlightRelation() { }

	public FlightRelation(Long id, Airport from, Airport to, double totalPrice) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.totalPrice = totalPrice;
	}

	@Override
	public Object getSource() {
		return from;
	}

	@Override
	public Object getTarget() {
		return to;
	}

	@Override
	protected double getWeight() {
		return totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Airport getFrom() {
		return from;
	}

	public void setFrom(Airport from) {
		this.from = from;
	}

	public Airport getTo() {
		return to;
	}

	public void setTo(Airport to) {
		this.to = to;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
