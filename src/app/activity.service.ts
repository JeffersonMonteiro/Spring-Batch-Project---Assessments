import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Activity } from './activity';
import { MessageService } from './message.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ActivityService {

  constructor(private http: HttpClient, private messageService: MessageService) { }

  private log(message: string) {
    this.messageService.add(`ActivityService: ${message}`);
  }

  private activityUrl = 'http://localhost:8080/activity';

  getActivities(): Observable<Activity[]> {
    return this.http.get<Activity[]>(this.activityUrl + '/get');
  }

  getActivity(id: number): Observable<Activity> {
    return this.http.get<Activity>(this.activityUrl + '/get/' + id);
  }

  createActivity(activity: Activity, voluntId: number): Observable<Activity> {
    return this.http.post<Activity>(this.activityUrl + '/add/' + voluntId, activity, httpOptions);
  }

  updateActivity(activity: Activity): Observable<any> {
    return this.http.put(this.activityUrl + '/update/' + activity.id, activity, httpOptions);
  }

  deleteActivity(id: number): Observable<Activity> {
    return this.http.delete<Activity>(this.activityUrl + '/delete/' + id);
  }

}
