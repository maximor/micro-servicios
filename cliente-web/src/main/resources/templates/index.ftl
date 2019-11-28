<#include "menu/header.ftl">
<#include "menu/sidebar.ftl">
<div class="container-fluid">
    <#if usuario?? && (usuario.getRoles()[0].getNombre() == "ROLE_ADMIN"
    || usuario.getRoles()[0].getNombre() == "ROLE_EMPLEADO")>
        <h3 class="page-title">Análisis</h3>
        <div class="row">
            <div class="col-4">
                <div class="bg-dark p-10 text-white text-center">
                    <i class="fa fa-exclamation-triangle m-b-5 font-16"></i>
                    <h5 class="m-b-0 m-t-5">
                        <#if estadistica??>
                            ${estadistica.getComprasPendientes()}
                        </#if>
                    </h5>
                    <small class="font-light">Ordenes Pendientes</small>
                </div>
            </div>
            <div class="col-4">
                <div class="bg-dark p-10 text-white text-center">
                    <i class="fa fa-check-circle m-b-5 font-16"></i>
                    <h5 class="m-b-0 m-t-5">
                        <#if estadistica??>
                            ${estadistica.getComprasRealizadas()}
                        </#if>
                    </h5>
                    <small class="font-light">Ordenes Realizadas</small>
                </div>
            </div>
            <div class="col-4">
                <div class="bg-dark p-10 text-white text-center">
                    <i class="fa fa-calendar m-b-5 font-16"></i>
                    <h5 class="m-b-0 m-t-5">
                        <#if estadistica??>
                            ${estadistica.getComprasPorDia()}
                        </#if>
                    </h5>
                    <small class="font-light">Ordenes del Día</small>
                </div>
            </div>
        </div>
        <br>
        <br>
        <hr>
    </#if>


    <h3 class="page-title">Seleccione el producto que quiere comprar</h3>

    <div class="row">
        <#if planes??>
            <#list planes as plan>
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${plan.getNombre()}</h5>
                            <p>${plan.getDescripcion()}</p>
                            <span class="badge badge-secondary">$RD ${plan.getMonto()}</span><br/>
                            <hr>
                            <button class="btn btn-warning">
                                <a href="/${plan.getNombre()}/" onclick="" >Agregar a Carrito</a>
                            </button>

                        </div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>

</div>
<#include "menu/footer.ftl">