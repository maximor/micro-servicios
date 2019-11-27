<#include "../menu/header.ftl">
<#include "../menu/sidebar.ftl">
<div class="container-fluid">
    <div class="row">
        <div class="col-12">
            <#if mensajeExitoso??>
                <div class="alert alert-success" role="alert">
                    <h4 class="alert-heading">${mensajeExitoso}</h4>

                </div>
            </#if>
            <#if mensajeError??>
                <div class="alert alert-danger" role="alert">
                    <h4 class="alert-heading">${mensajeError}</h4>
                </div>
            </#if>
        </div>
    </div>
    <h3 class="page-title">Manejo de Usuarios</h3>

    <div class="card">
        <form class="form-horizontal" action="/usuarios" method="post">
            <div class="card-body">
                <h4 class="card-title">Crear Empleados</h4>
                <div class="form-group row">
                    <label for="fname" class="col-sm-3 text-right control-label col-form-label">Usuario</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="fname" name="username" placeholder="Nombre de Usuario" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="lname" class="col-sm-3 text-right control-label col-form-label">Nombre</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="lname" name="nombre" placeholder="Nombres" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="lname" class="col-sm-3 text-right control-label col-form-label">Email</label>
                    <div class="col-sm-9">
                        <input type="email" class="form-control" id="lname" name="email" placeholder="Email" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="lname" class="col-sm-3 text-right control-label col-form-label">Password</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="lname" name="password" placeholder="Contraseña">
                    </div>
                </div>
            </div>
            <div class="border-top">
                <div class="card-body">
                    <button type="submit" name="submit" value="Submit" class="btn btn-warning">Crear</button>
                </div>
            </div>
        </form>
    </div>

</div>
<#include "../menu/footer.ftl">