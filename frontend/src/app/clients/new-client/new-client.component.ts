import { Component, Inject, OnDestroy } from '@angular/core';
import { ClientsService } from '../../services/api-client/clients/clients.service';
import { ICLientService } from '../../services/api-client/clients/iclients.service';
import { SERVICES_TOKEN } from '../../services/service.token';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ClientModelForm } from '../client.models';
import { ClientFormComponent } from '../components/client-form/client-form.component';
import { SnackbarManagerService } from '../../services/snackbar-manager.service';
import { ISnackbarManagerService } from '../../services/isnackbar-manager.service';

@Component({
  selector: 'app-new-client',
  imports: [ClientFormComponent],
  templateUrl: './new-client.component.html',
  styleUrl: './new-client.component.scss',
  providers: [
    { provide: SERVICES_TOKEN.HTTP.CLIENT, useClass: ClientsService },
    { provide: SERVICES_TOKEN.SNACKBAR, useClass: SnackbarManagerService },
  ],
})
export class NewClientComponent implements OnDestroy {
  private httpSubscription?: Subscription;
  constructor(
    @Inject(SERVICES_TOKEN.HTTP.CLIENT)
    private readonly httpService: ICLientService,
    @Inject(SERVICES_TOKEN.SNACKBAR)
    private readonly snackBarManager: ISnackbarManagerService,
    private readonly router: Router
  ) {}

  ngOnDestroy(): void {
    if (this.httpSubscription) {
      this.httpSubscription.unsubscribe();
    }
  }

  onSubmitClient(value: ClientModelForm) {
    console.log(value);
    const { id, ...request } = value;
    this.httpSubscription = this.httpService.save(request).subscribe((_) => {
      this.snackBarManager.show('Usuário cadastrado com sucesso');
      this.router.navigate(['clients/list']);
    });
  }
}
