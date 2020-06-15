package rs.ac.ni.pmf.web.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.web.model.entity.WorkerEntity;
import rs.ac.ni.pmf.web.repository.WorkersRepository;

@RequiredArgsConstructor
@Service
public class ArtisanApiUserDetailsService implements UserDetailsService {

	private final WorkersRepository workersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final WorkerEntity workerEntity = workersRepository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
		return new UserDetailsImpl(workerEntity);
	}
	
	@RequiredArgsConstructor
	private final class UserDetailsImpl implements UserDetails {

		private static final long serialVersionUID = 1L;
		private static final String ROLE_PREFIX = "ROLE_";
		
		private final WorkerEntity workerEntity;
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Arrays.asList(new SimpleGrantedAuthority(ROLE_PREFIX + "ADMIN"));
		}

		@Override
		public String getPassword() {
			return workerEntity.getPassword();
		}

		@Override
		public String getUsername() {
			return workerEntity.getUsername();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
		
	}

}
