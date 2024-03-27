package view;

import viewmodel.ViewModelFactory;

public interface ViewController {
    void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);
}
