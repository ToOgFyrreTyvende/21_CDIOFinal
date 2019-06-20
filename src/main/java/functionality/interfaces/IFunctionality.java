package functionality.interfaces;

import java.util.List;

public interface IFunctionality<T> {
    void createDTO(T DTO) throws Exception;

    T getDTO(int DTOId) throws Exception;

    List<T> getAllDTOs() throws Exception;

    void updateDTO(T DTO) throws Exception;

    void deleteDTO(T DTO) throws Exception;
}
