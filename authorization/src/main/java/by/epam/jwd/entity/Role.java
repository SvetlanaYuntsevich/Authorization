package by.epam.jwd.entity;

public enum Role {
	CLIENT(1), AGENT(2);

	private int id;

	private Role(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static Role getValue(int id) {
		Role role = null;
		if (id == 1) {
			role = CLIENT;
		} else if (id == 2) {
			role = AGENT;
		}
		return role;
	}
}