<#include "menu/header.ftl">
<#include "menu/sidebar.ftl">
<div class="container-fluid">

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Mis Ordenes Abiertos</h5>
            <div class="table-responsive">
                <table id="zero_config" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Productos</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if ordenesAbiertas??>
                        <#list ordenesAbiertas as ordeneAbierta>
                            <tr>
                                <td>${ordeneAbierta.getId()}</td>
                                <td>${ordeneAbierta.getMontoTotal()}</td>
                                <td>
                                    <#if ordeneAbierta.isAbierta() == true >
                                        Abierta
                                    <#else>
                                        Cerrada
                                    </#if>
                                </td>
                                <td>
                                    <#list ordeneAbierta.getPlanes() as plan>
                                        ${plan.getNombre()},
                                    </#list>
                                </td>

                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Productos</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Mis Ordenes Cerrados</h5>
            <div class="table-responsive">
                <table id="zero_config1" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Productos</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if ordenesAbiertas??>
                        <#list ordenesCerradas as ordeneCerrada>
                            <tr>
                                <td>${ordeneCerrada.getId()}</td>
                                <td>${ordeneCerrada.getMontoTotal()}</td>
                                <td>
                                    <#if ordeneCerrada.isAbierta() == true >
                                        Abierta
                                    <#else>
                                        Cerrada
                                    </#if>
                                </td>
                                <td>
                                    <#list ordeneCerrada.getPlanes() as plan>
                                        ${plan.getNombre()},
                                    </#list>
                                </td>

                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>Total</th>
                        <th>Estado</th>
                        <th>Productos</th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<#include "menu/footer.ftl">
<script>
    /****************************************
     *       Basic Table                   *
     ****************************************/
    $('#zero_config').DataTable();
    $('#zero_config1').DataTable();
</script>