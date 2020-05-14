package ca.mcgill.ecse321.ecse321project.dto;

public class RoomDto {
	private String description;
	private String code;
	private boolean bigRoom;
	
	public RoomDto() {
	}
	public RoomDto(String description, String code,Boolean bigRoom) {
		this.description = description;
		this.code = code;
		this.bigRoom = bigRoom;

	}
	public String getDescription() {
		return description;
	}
	public String getCode() {
		return code;
	}
	public boolean getBigRoom() {
		return bigRoom;
	}

}
