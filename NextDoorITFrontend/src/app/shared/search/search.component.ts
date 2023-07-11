import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  @Output() searchEvent = new EventEmitter<string>();
  constructor() { }

  ngOnInit(): void {
  }

  onSearch(text: string) {
    this.searchEvent.emit(text);
  }

  onClear() {
    this.searchEvent.emit('');
  }

}
