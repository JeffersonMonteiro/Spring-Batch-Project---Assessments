import { Activity } from './activity';

export class Volunteer {
  id: number;
  name: string;
  age: number;
  amntBuilding: number;
  amntSurvey: number;
  active: Boolean;
  activityList: Activity[];
}
