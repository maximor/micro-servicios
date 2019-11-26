<#include "menu/header.ftl">
<#include "menu/sidebar.ftl">
<div class="container-fluid">
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
                            <a href="/${plan.getNombre()}" class="btn btn-warning">Agregar a Carrito</a>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>

</div>
<#include "menu/footer.ftl">