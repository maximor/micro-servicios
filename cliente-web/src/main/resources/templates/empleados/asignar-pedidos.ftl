<#include "../menu/header.ftl">
<#include "../menu/sidebar.ftl">
<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Ordenes Abiertas</h5>
            <div class="table-responsive">
                <table id="zero_config" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Email</th>
                        <th>Total</th>
                        <th>Planes</th>
                        <th>Acción</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if ordenes??>
                        <#list ordenes as orden>
                            <tr>
                                <td>${orden.getId()}</td>
                                <td>${orden.getEmail()}</td>
                                <td>$${orden.getMontoTotal()}</td>
                                <td>
                                    <#list orden.getPlanes() as plan>
                                        ${plan.getNombre()},
                                    </#list>
                                </td>

                                <td>
                                    <a href="/cerrar-pedido/${orden.getId()}"  class="btn btn-warning">Cerrar</a>
                                </td>
                            </tr>
                        </#list>
                    </#if>

                    </tbody>
                    <tfoot>
                    <tr>
                        <th>Id</th>
                        <th>Email</th>
                        <th>Total</th>
                        <th>Planes</th>
                        <th>Acción</th>
                    </tr>
                    </tfoot>
                </table>
            </div>

        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Ordenes Cerradas</h5>
            <div class="table-responsive">
                <table id="zero_config1" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Email</th>
                        <th>Total</th>
                        <th>Planes</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if ordenesCerradas??>
                        <#list ordenesCerradas as orden>
                            <tr>
                                <td>${orden.getId()}</td>
                                <td>${orden.getEmail()}</td>
                                <td>$${orden.getMontoTotal()}</td>
                                <td>
                                    <#list orden.getPlanes() as plan>
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
                        <th>Email</th>
                        <th>Total</th>
                        <th>Planes</th>
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