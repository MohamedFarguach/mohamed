package ma.richebois.transport.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ma.richebois.transport.dto.AppRoleDTO;
import ma.richebois.transport.entity.AppRole;
import ma.richebois.transport.mapper.MyMapper;
import ma.richebois.transport.repositorie.AppRoleRepository;

@Transactional
@Slf4j
@Service

public class AppRoleService {
	@Autowired
	  private  AppRoleRepository appRoleRepository;

	
	@Autowired
	  private  MyMapper myMapper;

////////Affichage Role ////////////////
public List<AppRoleDTO> GetRoles(){
List<AppRole> appRole =   appRoleRepository.findAll();
try {

return     appRole.stream().map(myMapper::toAppRoleDTO).collect(Collectors.toList());
} catch (Exception e) {
log.trace("  la methode AppRoleDTO"+appRole);
System.out.println("GetAppRoleDTO methode exception"+e);
return null;

}
}
}
