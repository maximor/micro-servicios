<#include "menu/header.ftl">
<#include "menu/sidebar.ftl">
<div class="row">
    <div class="col-md-12">
        <#if mensajeUsuario??>
            <div class="alert alert-success" role="alert">
                <h4 class="alert-heading">${mensajeUsuario}</h4>
            </div>
        </#if>
    </div>
    <div class="col-md-12">
        <div class="card">
            <form class="form-horizontal" action="/perfil" method="post">
                <div class="card-body">
                    <h4 class="card-title">Informacion Personal</h4>
                    <div class="form-group row">
                        <label for="fname" class="col-sm-3 text-right control-label col-form-label">Nombre de Usuario</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="fname" name="username" value="${usuario.getUsername()}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="lname" class="col-sm-3 text-right control-label col-form-label">Nombre</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="lname" name="nombre" value="${usuario.getNombre()}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="email1" class="col-sm-3 text-right control-label col-form-label">Email</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="email1" name="email" value="${usuario.getEmail()}">
                        </div>
                    </div>
                </div>
                <div class="border-top">
                    <div class="card-body">
                        <button type="submit" value="Submit" class="btn btn-success" >Actualizar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="card">
            <form class="form-horizontal" action="/perfil" method="post">
                <div class="card-body">
                    <h4 class="card-title">Securidad - Cambiar Contraseña</h4>
                    <div class="form-group row">
                        <label for="fname" class="col-sm-3 text-right control-label col-form-label">Contraseña Vieja</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="fname" name="oldPassword" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="lname" class="col-sm-3 text-right control-label col-form-label">Nueva Contraseña</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="lname" name="newPassword" required>
                        </div>
                    </div>
                </div>
                <div class="border-top">
                    <div class="card-body">
                        <button type="submit" value="Submit" class="btn btn-success" >Actualizar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<#if mensaje??>
    <div class="alert alert-success" role="alert">
        <h4 class="alert-heading">${mensaje}</h4>
    </div>
</#if>
<#if mensajeError??>
    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">${mensajeError}</h4>
    </div>
</#if>
<#include "menu/footer.ftl">