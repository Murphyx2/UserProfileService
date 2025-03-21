package com.app.userprofile.domain.userprofile.output;

import com.app.userprofile.domain.userprofile.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserProfileResponse {

	private UserProfile userProfile;

	public CreateUserProfileResponse withUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
		return this;
	}

}
