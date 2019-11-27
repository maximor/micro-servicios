<#include "menu/header.ftl">
<#include "menu/sidebar.ftl">
<div class="container-fluid">

    <div class="row">
        <div class="col-9">
            <h2 class="page-title">Mi Carrito</h2>
        </div>
        <#if planesCarrito?? && cantidad?? && (cantidad > 0)>
            <div class="col-3">
                <a href="/checkout" class="btn btn-lg btn-warning">Generar Orden</a>
            </div>
        </#if>
    </div>
    <br>
    <div class="row">
        <#if planesCarrito?? && cantidad?? && (cantidad > 0)>

            <#list planesCarrito as plan>
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title">${plan.getNombre()}</h3>
                            <div class="row">
                                <div class="col-9">
                                    <p>${plan.getDescripcion()}</p>
                                </div>
                                <div class="col-3">
                                    <h3><strong class="text-danger floating-right">$RD ${plan.getMonto()}</strong></h3>
                                </div>
                                <div class="col-12">
                                    <a href="/eliminarCarrito/${plan?counter}" class="btn btn-danger">Eliminar</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </#list>

        <#else>
            <div class="error-box">
                <div class="error-body text-center">
                    <h1 class="error-title text-warning">BIENVENIDO</h1>
                    <h3 class="text-uppercase error-subtitle">A TU CARRITO DE COMPRAS</h3>
                    <p class="text-muted m-t-30 m-b-30">Debe de seleccionar al menos un producto</p>
                    <a href="/" class="btn btn-danger btn-rounded waves-effect waves-light m-b-40">Seleccionar producto</a> </div>
            </div>
        </#if>

    </div>
    <br>
    <div class="row">
        <#if planesCarrito?? && cantidad?? && (cantidad > 0)>
            <div class="col-9">
                <h2 class="page-title">Mi Carrito</h2>
            </div>
            <div class="col-3">
                <a href="/checkout" class="btn btn-lg btn-warning">Generar Orden</a>
            </div>
        </#if>
    </div>

</div>
<#include "menu/footer.ftl">