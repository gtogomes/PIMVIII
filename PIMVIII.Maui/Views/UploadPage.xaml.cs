namespace PIMVIII.Maui.Views;

public partial class UploadPage : ContentPage
{
    public UploadPage()
    {
        InitializeComponent();
    }

    private async void SelectFile(object sender, EventArgs e)
    {
        // FilePicker SEM RESTRIÇÕES – aprovado para Windows/Android/iOS
        var result = await FilePicker.Default.PickAsync(new PickOptions
        {
            PickerTitle = "Selecione um arquivo"
        });

        if (result != null)
            SelectedFileLabel.Text = $"Arquivo selecionado: {result.FileName}";
    }

    private async void SendFile(object sender, EventArgs e)
    {
        await DisplayAlert("Sucesso", "Conteúdo enviado com sucesso!", "OK");
    }
}
