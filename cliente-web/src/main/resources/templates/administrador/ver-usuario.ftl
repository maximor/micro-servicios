<#include "../menu/header.ftl">
<#include "../menu/sidebar.ftl">
<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Empleados</h5>
            <div class="table-responsive">
                <table id="zero_config" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Usuario</th>
                        <th>Nombres</th>
                        <th>Email</th>
                        <th>Rol</th>
                        <th>Activo</th>
                        <th>Acción</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if usuarios??>
                        <#list usuarios as usuario>
                            <tr>
                                <td>${usuario.getId()}</td>
                                <td>${usuario.getUsername()}</td>
                                <td>${usuario.getNombre()}</td>
                                <td>${usuario.getEmail()}</td>
                                <td>${usuario.getRoles()[0].getNombre()}</td>
                                <td>
                                    <#if usuario.isActivo()>
                                        Sí
                                    <#else>
                                        No
                                    </#if>
                                </td>
                                <td>
                                    <#if usuario.getRoles()[0].getNombre() != "ROLE_ADMIN">
                                        <#if usuario.isActivo()>
                                            <a class="btn btn-danger" href="/ver-usuarios/${usuario.getUsername()}/0">Desactivar</a>
                                        <#else>
                                            <a class="btn btn-warning" href="/ver-usuarios/${usuario.getUsername()}/1">Activar</a>
                                        </#if>
                                    <#else>
                                        N/A
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                    </#if>

                    </tbody>
                    <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>Usuario</th>
                        <th>Nombres</th>
                        <th>Email</th>
                        <th>Rol</th>
                        <th>Activo</th>
                        <th>Acción</th>
                    </tr>
                    </tfoot>
                </table>
            </div>

        </div>
    </div>


</div>
<#include "../menu/footer.ftl">

<script>
    /****************************************
     *       Basic Table                   *
     ****************************************/
    $('#zero_config').DataTable();
    $('#zero_config1').DataTable();
</script>