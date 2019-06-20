package ac.daffodil.amirul.backend.service;

import ac.daffodil.amirul.backend.GroupRepository;
import ac.daffodil.amirul.backend.data.entity.Groups;
import ac.daffodil.amirul.backend.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService extends CrudService<Groups> {

	private final GroupRepository groupRepository;

	@Autowired
	public GroupService(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public Page<Groups> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByGroupNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getRepository().findAll(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByGroupNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	public Groups getDefault() {
		return findAnyMatching(Optional.empty(), new PageRequest(0, 1)).iterator().next();
	}

	@Override
	protected GroupRepository getRepository() {
		return groupRepository;
	}

}