<#include "menu/header.ftl">
<#include "menu/sidebar.ftl">
<div class="row">
    <div class="col-md-12">
        <div class="card card-body printableArea">
            <h3><b>Orden</b> <span class="pull-right"></span></h3>
            <hr>
            <div class="row">
                <div class="col-md-12">
                    <div class="pull-left">
                        <address>
                            <h3> &nbsp;<b class="text-danger">Matrix Fotos</b></h3>
                            <p class="text-muted m-l-5">E 104, Dharti-2,
                                <br/> Nr' Viswakarma Temple,
                                <br/> Talaja Road,
                                <br/> Bhavnagar - 364002</p>
                        </address>
                    </div>
<#--                    <div class="pull-right text-right">-->
<#--                        <address>-->
<#--                            <h3>To,</h3>-->
<#--                            <h4 class="font-bold">Gaala & Sons,</h4>-->
<#--                            <p class="text-muted m-l-30">E 104, Dharti-2,-->
<#--                                <br/> Nr' Viswakarma Temple,-->
<#--                                <br/> Talaja Road,-->
<#--                                <br/> Bhavnagar - 364002</p>-->
<#--                            <p class="m-t-30"><b>Invoice Date :</b> <i class="fa fa-calendar"></i> 23rd Jan 2018</p>-->
<#--                            <p><b>Due Date :</b> <i class="fa fa-calendar"></i> 25th Jan 2018</p>-->
<#--                        </address>-->
<#--                    </div>-->
                </div>
                <div class="col-md-12">
                    <div class="table-responsive m-t-40" style="clear: both;">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th class="">Nombre</th>
<#--                                <th class="text-right">Cantidad</th>-->
                                <th class="text-right">Costo Unitario</th>
<#--                                <th class="text-right">Total</th>-->
                            </tr>
                            </thead>
                            <tbody>
                            <#if orden??>
                                <#list orden.getPlanes() as plan>
                                    <tr>
                                        <#--                                <td class="text-center">1</td>-->
                                        <td>${plan.getNombre()}</td>
                                        <td class="text-right">${plan.getMonto()}</td>
                                        <#--                                <td class="text-right"> $24 </td>-->
                                        <#--                                <td class="text-right"> $48 </td>-->
                                    </tr>
                                </#list>
                            </#if>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="pull-right m-t-30 text-right">
<#--                        <p>Sub - Total amount: $13,848</p>-->
<#--                        <p>vat (10%) : $138 </p>-->
<#--                        <hr>-->
                        <#if orden??>
                            <h3><b>Total :</b> $${orden.getMontoTotal()}</h3>
                        </#if>

                    </div>
                    <div class="clearfix"></div>
                    <hr>
                    <div class="text-left">
                        <a class="btn btn-warning" href="/carrito"> Ir al Carrito </a>
                    </div>
                    <div class="text-right">
                        <a class="btn btn-danger" href="/pagar"> Proceder al Pago </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "menu/footer.ftl">