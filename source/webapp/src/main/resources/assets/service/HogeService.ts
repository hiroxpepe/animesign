
import { Hoge } from '../entity/Hoge';
import { StringUtils } from '../util/StringUtils';

export class HogeService {
    constructor(private hoge: Hoge) {
    }
    sayName(): string {
        return StringUtils.getUpperCase(this.hoge.getName());
    }
}