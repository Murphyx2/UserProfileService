package com.app.userprofile.domain.userprofile.output;

import com.app.userprofile.domain.userprofile.UserProfile;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserProfileResponse {

	private UserProfile firstName;

}
