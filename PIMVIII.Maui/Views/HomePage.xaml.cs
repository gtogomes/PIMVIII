using PIMVIII.Maui.Views;

namespace PIMVIII.Maui.Views;

public partial class HomePage : ContentPage
{
    public HomePage()
    {
        InitializeComponent();
    }

    private async void GoToUpload(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new UploadPage());
    }

    private async void GoToPlaylists(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new PlaylistsPage());
    }

    private async void GoToMetrics(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new MetricsPage());
    }
}
